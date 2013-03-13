(ns chabad.views.layout
  (:use [hiccup core page]
        chabad.core
        (chabad.helpers [routing :only [url-for]])))

(defn- include-app-js
  []
  [(when (dev-env?) (include-js "/js/goog/base.js"))
   (include-js "/js/app.js")
   (when (dev-env?) [:script "goog.require('chabad.app');"])
   [:script "chabad.app.main();"]])

(defn- navbar
  []
  [:div.navbar.navbar-fixed-top
   [:div.navbar-inner
    [:a.brand {:href (url-for :core-root)} "Chabad"]
    [:ul.nav
     [:li [:a {:href (url-for :user-session-new)} "Log In"]]]]])

(defn- app-body
  [content]
  (vec (concat [:body [:div.container (navbar) content] 
                      (include-js "/js/jquery-1.9.1.min.js")
                      (include-js "/js/bootstrap.min.js")]
               (include-app-js))))

(defn app
  [& content]
  (html5 [:head (include-css "/css/bootstrap/bootstrap.min.css")
                (include-css "/css/app.css")]
         (app-body content)))
