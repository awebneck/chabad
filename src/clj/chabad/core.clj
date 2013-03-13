(ns chabad.core)

(defn env
  [key]
  (get (System/getenv) (name key)))

(defn dev-env?
  []
  (= (env :APPENV) "development"))
