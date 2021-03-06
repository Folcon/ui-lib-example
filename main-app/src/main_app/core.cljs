(ns ^:figwheel-hooks main-app.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as rdom]
   [main-app.runtime :refer [app-panel]]
   [main-app.routes :refer [app-routes]]))


(defn get-app-element []
  (gdom/getElement "app"))

(defn mount [el]
  (rdom/render [app-panel] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (app-routes)
    (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element))
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)

