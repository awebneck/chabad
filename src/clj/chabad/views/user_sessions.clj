(ns chabad.views.user-sessions
  (:use [hiccup.core])
  (:require [chabad.views.layout :as layout]))

(defn new
  []
  (layout/app [:h1 "Login Form"]))
