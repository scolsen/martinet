(ns martinet.validate
  "Validating the contents of JSON files based on a provided spec."
  (:require [clojure.spec.alpha :as spec] [clojure.string :as string]))

(defn find-specs
  "List specs loaded into the spec registry that contain a term."
  [term]
  (let [pred #(and (string/includes? (namespace %) term) (keyword? %))]
    (->> (spec/registry)
         (filter pred))))

(defn mark
  "Mark the places in which a non-conformant JSON object fails to conform to a spec."
  ([sp json]
   (-> (spec/explain-data sp json)
       (get :clojure.spec.alpha/problems)
       (->> (map #(get % :path)))))
  ([sp json tag]))

(def valid? spec/valid?)
(def conform spec/conform)
(def explain spec/explain-data)
