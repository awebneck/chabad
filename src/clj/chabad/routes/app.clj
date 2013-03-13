(ns chabad.routes.app
  (:use chabad.helpers.routing)
  (:use compojure.core)
  (:require [compojure.route :as route]
            [chabad.controllers [welcome :as welcome]
                                [user-sessions :as user-sessions]]))

(defroutemap core
  :root (GET "/" [] welcome/show))

(defroutemap user-session
  :new (GET "/login" [] user-sessions/new)
  :create (POST "/login" [] user-sessions/create))

(def app
  (routes core
          user-session
          (route/resources "/")
          (route/not-found "Eff this")))
