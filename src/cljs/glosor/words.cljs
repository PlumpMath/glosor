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
   :tags #{:subs :sing :nom :obest gender}})

(defn subs-nom-plur [isl sve gender]
  {:isl isl
   :sve sve
   :tags #{:subs :plur :nom :obest gender}})

(defn sub [isl sve gender kasus numerativ]
  {:isl isl
   :sve sve
   :tags #{:subs gender kasus numerativ}})

(defn subs-suit [nom-sing nom-sing-best nom-plur nom-plur-best
                 ack-sing ack-sing-best ack-plur ack-plur-best
                 dat-sing dat-sing-best dat-plur dat-plur-best
                 gen-sing gen-sing-best gen-plur gen-plur-best 
                 sve-sing sve-sing-best sve-plur sve-plur-best gender]
  [(sub nom-sing        sve-sing       gender :nom :sing)
   (sub nom-sing-best   sve-sing-best  gender :nom :sing)
   (sub nom-plur        sve-plur       gender :nom :plur)
   (sub nom-plur-best   sve-plur-best  gender :nom :plur)
   (sub ack-sing        sve-sing       gender :ack :sing)
   (sub ack-sing-best   sve-sing-best  gender :ack :sing)
   (sub ack-plur        sve-plur       gender :ack :plur)
   (sub ack-plur-best   sve-plur-best  gender :ack :plur)
   (sub dat-sing        sve-sing       gender :dat :sing)
   (sub dat-sing-best   sve-sing-best  gender :dat :sing)
   (sub dat-plur        sve-plur       gender :dat :plur)
   (sub dat-plur-best   sve-plur-best  gender :dat :plur)
   (sub gen-sing        sve-sing       gender :gen :sing)
   (sub gen-sing-best   sve-sing-best  gender :gen :sing)
   (sub gen-plur        sve-plur       gender :gen :plur)
   (sub gen-plur-best   sve-plur-best  gender :gen :plur)])

(defn adj [isl sve]
  {:isl isl
   :sve sve})

(def hestur (subs-suit "hestur" "hesturinn" "hestar"   "hestarnir"
                       "hest"   "hestinn"   "hesta"    "hestana"
                       "hesti"  "hestinum"  "hestum"   "hestunum"
                       "hests"  "hestsins"  "hesta"    "hestanna"
                       "häst"   "hästen"    "hästar"   "hästarna"
                       :mask))

(def fjall (subs-suit "fjall" "fjallið" "fjöll" "fjöllin"
                      "fjall" "fjallið" "fjöll" "fjöllin"
                      "fjalli" "fjallinu" "fjöllum" "fjöllunum"
                      "fjalls" "fjallsins" "fjalla" "fjallanna"
                      "fjäll" "fjället" "fjäll" "fjällen"
                      :neutr))

(def substantiv
  [(subs-nom "gólf" "golv" :neutr)
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
   (subs-nom "ást" "kärlek" :fem)
   (subs-nom "mál" "måltid" :neutr)
   (subs-nom "hjarta" "hjärta" :neutr)
   (subs-nom "orsök" "orsak" :fem)
   (subs-nom "vandi" "problem" :mask)
   (subs-nom "þvæla" "strunt" :fem)
   (subs-nom "lygi" "lögn" :fem)
   (subs-nom "mamma" "mamma" :fem)])

(def all-items (concat [] fjall))

