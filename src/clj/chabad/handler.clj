(ns chabad.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [chabad.helpers.routing :as routing]
            [chabad.routes.app :as app]))

(def app
  (handler/site app/app))
