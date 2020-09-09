(ns main-app.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:import [goog History]
           [goog.history Html5History EventType])
  (:require [secretary.core :as secretary]
            [goog.events :as gevents]
            [re-frame.core :as rf]))


;;; Events
(rf/reg-event-db
  ::set-page
  (fn [_db [_ page]]
    (assoc {} ::current-page page)))


;;; Subs
(rf/reg-sub
  ::current-page
  (fn [db [_]]
    (get db ::current-page)))


(defn get-token []
  js/window.location.hash)

(defn make-history []
  (doto (Html5History.)
    (.setPathPrefix (str js/window.location.protocol
                         "//"
                         js/window.location.host))
    (.setUseFragment true)))

(defn handle-url-change [e]
  ;; pop in the token to easily see when we're naving
  (js/console.log (str "Nav To: " (get-token)))
  ;; we are checking if this event is due to user action,
  ;; such as click a link, a back button, etc.
  ;; as opposed to programmatically setting the URL with the API
  (when-not (.-isNavigation e)
    ;; in this case, we're setting it
    (js/console.log "Token set programmatically")
    ;; let's scroll to the top to simulate a navigation
    (js/window.scrollTo 0 0))
  ;; dispatch on the token
  (secretary/dispatch! (get-token)))

(defonce history (make-history))

(defn hook-browser-navigation! []
  (doto history
    (gevents/listen
      EventType.NAVIGATE
      ;; wrap in a fn to allow live reloading
      #(handle-url-change %))
    (.setEnabled true)))

(defn nav! [token]
  (.setToken history token))

(defn make-link
  ([href-str] (make-link {} href-str href-str))
  ([attrs href-str] (make-link attrs href-str href-str))
  ([attrs href href-str] (merge attrs
                           {:href href
                            :on-click #(do
                                         (.preventDefault %)
                                         (nav! href-str))})))

(defn make-home-link []
  (make-link {} js/window.location.href "/"))

(defn make-page-link [page-kw]
  (let [path (str "/" (name page-kw))]
    (make-link {} path path)))

(defn app-routes []
  (secretary/set-config! :prefix "#")
  ;; --------------------
  ;; define routes here
  (defroute "/" []
    (rf/dispatch [::set-page nil]))

  (defroute "/about" []
    (rf/dispatch [::set-page :about-page]))


  ;; --------------------
  (hook-browser-navigation!))