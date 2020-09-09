(ns main-app.runtime
  (:require [re-frame.core :as rf]
            [ui-lib.layout.index :as index]
            [main-app.routes :as routes]))


(defn nav-bar-panel []
  [:nav
   [:a (routes/make-home-link) "Main App"]
   [:div
    [:ul
     [:li [:a (routes/make-home-link) "Home"]]
     [:li [:a (routes/make-page-link :about) "About"]]]]])


(defn app-panel []
  (let [current-page (rf/subscribe [::routes/current-page])]
    (fn []
      [:div
       [nav-bar-panel]
       (condp = @current-page
         :about-page
         [index/about-page]
         [index/home-page])])))
