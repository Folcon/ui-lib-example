(defproject main-app "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.9.4"

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.773"]
                 [reagent "0.10.0"]
                 [re-frame "1.0.0-rc2"]
                 [re-com "2.8.0"]
                 [clj-commons/secretary "1.2.5-SNAPSHOT"]
                 [ui-lib "0.1.0-SNAPSHOT"]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["target" "resources/public/cljs-out"]

  :aliases {"fig"       ["trampoline" "run" "-m" "figwheel.main"]
            "fig:dev"   ["trampoline" "run" "-m" "figwheel.main" "-b" "dev" "-r"]
            "fig:min"   ["run" "-m" "figwheel.main" "-O" "advanced" "-bo" "dev"]
            "fig:test"  ["run" "-m" "figwheel.main" "-co" "test.cljs.edn" "-m" "main-app.test-runner"]}

  :profiles {:dev {:dependencies [[com.bhauman/figwheel-main "0.2.11"]
                                  [com.bhauman/rebel-readline-cljs "0.1.4"]]
                   :source-paths ["checkouts/ui-lib"]
                   :resource-paths ["target"]
                   ;; need to add the compiled assets to the :clean-targets
                   :clean-targets ^{:protect false} ["target"]}})

