(ns chabad.helpers.routing
  (:use compojure.core)
  (:require [clojure.string :as string]
            [clojure.tools.macro :as macro]))

(def routes-list (atom {}))

(defn- interpolate-defined-params
  [route params]
  (reduce (fn [[uri unallocated] [key value]]
            (let [re (re-pattern (str (keyword key)))]
              (if (re-find re uri)
                [(string/replace uri re (str value)) unallocated]
                [uri (assoc unallocated (keyword key) value)])))
          [route {}]
          params))

(defn- interpolate-undefined-params
  [uri params]
  (if (empty? params)
    uri
    (string/replace (let [uri (str uri "?")]
                      (reduce (fn [uri [key value]]
                                (str uri (name key) "=" value "&"))
                              uri
                              params))
                    #"&$"
                    "")))

(defn interpolate-route-params
  [route params]
  (let [[uri unallocated] (interpolate-defined-params route params)]
    (interpolate-undefined-params uri unallocated)))

(defn get-route
  [routename]
  (@routes-list (keyword routename)))

(defn url-for
  [routename & [params]]
  (interpolate-route-params ((get-route routename) :route) (or params {})))

(defmacro mapped-routes
  [nomen routemap]
  (doseq [[k v] routemap]
    (swap! routes-list assoc
           (keyword (str (name nomen) "-" (name k)))
           {:method (keyword (string/lower-case (name (nth v 0))))
            :route (nth v 1)}) routemap)
  `(routes ~@(vals routemap)))

(defmacro defroutemap
  [name & routepairs]
  (let [routemap (apply hash-map routepairs)
        rts (reverse (vals routemap))
        [name rts] (macro/name-with-attributes name rts)]
    `(def ~name (mapped-routes ~name ~routemap))))
