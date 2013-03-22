(ns words)

(defn reset-indexes [items]
  (map #(assoc %1 :index %2) items (range)))

(defn reset-score [items]
  (map #(assoc % :score 0) items))

(defn reset [items]
  (-> items
      reset-indexes
      reset-score
      vec))

; {:isl "hestur" :sve "häst" :tags #{:subs :mask :sing :nom}}

(defn verb-inf [isl sve]
  {:isl isl
   :sve sve
   :tags #{:verb :inf}})

(defn subs-nom [isl sve gender]
  {:isl isl
   :sve sve
   :tags #{:subs :sing :nom gender}})

(defn subs-nom-plur [isl sve gender]
  {:isl isl
   :sve sve
   :tags #{:subs :plur :nom gender}})

(defn adj [isl sve]
  {:isl isl
   :sve sve})

(def all-items [
                (subs-nom "gólf" "golv" :neutr)
                (subs-nom "par" "par" :neutr)
                (subs-nom "skál" "skål" :fem)
                (subs-nom "þvottavél" "tvättmaskin" :fem)
                (subs-nom "rafmagn" "ström" :neutr)
                (subs-nom "eldavél" "spis" :fem)
                (subs-nom "prinsessa" "prinsessa" :fem)
                (subs-nom "skrifstofa" "kontor" :fem)
                (subs-nom "bréf" "brev" :neutr)
                (subs-nom "sími" "telefon" :mask)
                (subs-nom "skjal" "dokument" :neutr)
                (subs-nom-plur "stjórnmál" "politik" :neutr)
                (subs-nom "íhald" "konservatism" :neutr)
                (subs-nom "fundur" "möte" :mask)
                (subs-nom "eldur" "eld" :mask)
                (subs-nom "slökkvilið" "brandkår" :neutr)
                (subs-nom "efni" "material" :neutr)
                (subs-nom "kafli" "kapitel" :mask)
                (subs-nom "skógur" "skog" :mask)
                (subs-nom "hús" "hus" :neutr)
                (subs-nom "þak" "tak" :neutr)
                (subs-nom "gluggakarmur" "fönsterkarm" :mask)
                (subs-nom "dreki" "drake" :mask)
                (subs-nom "foreldri" "förälder" :neutr)
                (subs-nom "pabbi" "pappa" :mask)
                (subs-nom "land" "land" :neutr)
                (subs-nom "fjall" "fjäll" :neutr)
                (subs-nom "dalur" "dal" :mask)
                (subs-nom "eldhús" "kök" :neutr)
                (subs-nom "matur" "mat" :mask)
                (subs-nom "grautur" "gröt" :mask)
                (subs-nom "diskur" "tallrik" :mask)
                (subs-nom "stóll" "stol" :mask)
                (subs-nom "baki" "rygg" :neutr)
                (subs-nom "gestur" "gäst" :mask)
                (subs-nom "kaffi" "kaffe" :neutr)
                (subs-nom "svefnherbergi" "sovrum" :neutr)
                (subs-nom "rúm" "säng" :neutr)
                (subs-nom "gafl" "gavel" :mask)
                (subs-nom "sæng" "täcke" :fem)
                (subs-nom "morgunn" "morgon" :mask)
                (subs-nom "sól" "sol" :fem)
                (subs-nom "gluggi" "fönster" :mask)
                ])

