(defproject ui-lib "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.9.4"

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.773"]
                 [reagent "1.0.0-alpha2"]
                 [re-frame "1.0.0-rc2"]
                 [re-com "2.8.0"]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["target" "resources/public/js/compiled"]

  :aliases {"dev"       ["do" "clean," "fig:npm," "fig:dev"]
            "fig"       ["trampoline" "run" "-m" "figwheel.main"]
            "fig:npm"   ["run" "-m" "figwheel.main" "--" "--install-deps"]
            "fig:dev"   ["trampoline" "run" "-m" "figwheel.main" "-b" "dev" "-r"]
            "fig:min"   ["run" "-m" "figwheel.main" "-O" "advanced" "-bo" "dev"]
            "fig:test"  ["run" "-m" "figwheel.main" "-co" "test.cljs.edn" "-m" "ui-lib.test-runner"]}

  :profiles {:dev {:dependencies [[com.bhauman/figwheel-main "0.2.11"]
                                  [com.bhauman/rebel-readline-cljs "0.1.4"]]
                   
                   :resource-paths ["target"]
                   ;; need to add the compiled assets to the :clean-targets
                   :clean-targets ^{:protect false} ["target"]}})

