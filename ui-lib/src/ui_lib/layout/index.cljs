(ns ui-lib.layout.index
  (:require [ui-lib.layout.carousel :refer [carousel-panel]]))


(defn home-page []
  [:div
   [:h1 "Home Page"]
   [carousel-panel
    [{:id 0
      :content [:div [:p.testimonial "A test testimonial"]]}
     {:id 1
      :content [:div [:p.testimonial "Another test testimonial"]]}]]])

(defn about-page []
  [:div
   [:h1 "About Page"]])

