(ns chabad.views.welcome
  (:use [hiccup.core])
  (:require [chabad.views.layout :as layout]))

(defn show
  []
  (layout/app [:h1 "Howdy!"]))
