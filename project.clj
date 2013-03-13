(defproject chabad "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/tools.macro "0.1.2"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.2"]]
  :plugins [[lein-ring "0.8.2"]
            [lein-cljsbuild "0.3.0"]]
  :source-paths ["src/clj"]
  :ring {:handler chabad.handler/app}
  :profiles {:dev {:dependencies [[ring-mock "0.1.3"]]}}
  :cljsbuild {:builds {:main {:source-paths ["src/cljs"]
                              :compiler {:output-dir "resources/public/js"
                                         :output-to "resources/public/js/app.js"
                                         :optimizations :none}}}})
