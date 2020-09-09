(ns ui-lib.layout.carousel
  (:require [reagent.core :as r]
            [react]
            [react-transition-group :refer [Transition TransitionGroup CSSTransition]]))


(defn carousel-child
  [{:keys [direction children in]}]
  [:> CSSTransition {:in in
                     :timeout 500
                     :class-names {:enter (str "slide-enter-" (name direction))
                                   :enter-active "slide-enter-active"
                                   :exit "slide-exit"
                                   :exit-active (str "slide-exit-active-" (name direction))
                                   :exit-done "slide-exit-done"}}
   (fn [state]
     (r/as-element
       (into [:div {:class "slide-base"}] children)))])

(defn carousel
  [{:keys [direction class]}]
  (let [children (r/children (r/current-component))
        k (-> children first meta :key)]
    [:> TransitionGroup {:class ["transition-group" class]
                         :childFactory (fn [child]
                                         (react/cloneElement child #js {:direction direction}))}
     (let [child (r/reactify-component carousel-child)]
       (r/create-element child #js {:key k
                                    :direction direction
                                    :children children}))]))

(defn on-click
  [direction]
  (fn [{n :n}]
    {:n (case direction
          :left (dec n)
          :down (dec n)
          :up (inc n)
          :right (inc n))
     :dir direction}))


(defn carousel-panel [slides]
  (let [state (r/atom {:n 0 :dir :left})]
    (fn [slides]
      (let [size (count slides)]
        [:div.carousel
         [:div.container
          [:div
           (when (> size 1)
             [:div.col.col-md
              [:button {:on-click #(swap! state (on-click :left))}
               "<"]])
           [:div
            (let [slide (->> (count slides)
                          (mod (:n @state))
                          (nth slides))
                  {:keys [id colour content] :or {colour :white}} slide]
              [:div.frame {:style {:max-width "300px" :max-height "270px"}}
               [carousel {:direction (:dir @state)}
                ^{:key id}
                [:div.slide {:style {:background-color colour :min-width "200px" :min-height "250px" :border-radius "10px"}}
                 content]]])]
           (when (> size 1)
             [:div
              [:button {:on-click #(swap! state (on-click :right))}
               ">"]])]]]))))
