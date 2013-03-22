(ns words)

(def all-items [
                {:isl "hestur" :sve "häst" :tags #{:subs :mask :sing :nom}}
                {:isl "fjall" :sve "fjäll" :tags #{:subs :neutr :sing :nom}}
                {:isl "eldhús" :sve "kök" :tags #{:subs :neutr :sing :nom}}
                ])

(defn reset-indexes [items]
  (map #(assoc %1 :index %2) items (range)))

(defn reset-score [items]
  (map #(assoc % :score 0) items))

(defn reset [items]
  (-> items
      reset-indexes
      reset-score
      vec))