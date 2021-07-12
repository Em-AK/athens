(ns athens.mobile.views.demo
  (:require
    ["react-native" :as rn]
    [reagent.core :as r]))


;; must use defonce and must refresh full app so metro can fill these in
;; at live-reload time `require` does not exist and will cause errors
;; must use path relative to :output-dir

(defonce splash-img (js/require "../assets/logo.png"))


(def styles
  ^js (-> {:container
           {:flex 1
            :backgroundColor "#fff"
            :alignItems "center"
            :justifyContent "center"}
           :image
           {:width 200
            :height 200}
           :title
           {:fontWeight "bold"
            :fontSize 24
            :color "grey"}
           :text
           {:fontSize 18
            :color "grey"}}
          (clj->js)
          (rn/StyleSheet.create)))


(defonce touch-counter (r/atom 0))


(defn reset-touch-counter
  []
  (reset! touch-counter 0))


(defn trigger-alert
  []
  (rn/Alert.alert
    "WIP"
    "This is the end of the demo."
    (clj->js [{:text "Cancel"
               :style "cancel"}
              {:text "Start again"
               :onPress reset-touch-counter
               :style "default"}])))


(defn touch-logo
  []
  (if (> 3 @touch-counter)
    (swap! touch-counter inc)
    (trigger-alert)))


(defn demo-view
  []
  [:> rn/View {:style (.-container styles)}
   [:> rn/Pressable {:onPress touch-logo}
    [:> rn/Image {:source splash-img :style (.-image styles)}]]
   (when (< 0 @touch-counter)
     [:> rn/Text {:style (.-title styles)} "This is Athens"])
   (when (< 1 @touch-counter)
     [:> rn/Text {:style (.-text styles)} "Your research accelerator"])
   (when (< 2 @touch-counter)
     [:> rn/Text {:style (.-text styles)} "Right from your pocket!"])])
