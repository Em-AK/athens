(ns athens.mobile
  (:require
    [athens.mobile.views :as views]
    [reagent.core :as r]
    [shadow.react-native :refer (render-root)]))


(defn root
  []
  [views/app-demo])


(defn start
  "Called repeatedly when watch is running"
  {:dev/after-load true}
  []
  (render-root "Athens" (r/as-element [root])))


(defn init
  "Called once on startup"
  []
  (start))
