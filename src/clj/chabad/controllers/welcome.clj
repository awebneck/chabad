(ns chabad.controllers.welcome
  (:require [chabad.views.welcome :as view]))

(defn show
  [req]
  (view/show))
