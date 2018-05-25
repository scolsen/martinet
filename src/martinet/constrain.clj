(ns martinet.constrain
  "Contrain clojure specs at runtime."
  (:require [clojure.spec.alpha :as spec]))

(defmacro add-constraint-strict
  "Modify a spec with custom constraints."
  [spec-key predicate]
  (let [k (keyword spec-key)]
    `(spec/def ~k (spec/and (spec/get-spec ~k) ~predicate))))

;; TODO: Check that k is a namespaced key.
(defmacro redefine
  "Redefine a spec using a new predicate."
  ([k & preds] {:pre [(keyword? k)]}
   `(spec/def ~k (spec/and ~@preds))))
