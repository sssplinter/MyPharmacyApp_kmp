package data.entity

import kr.sementsova.composeapp.db.MedicineType

enum class MedicineTypeEnum(key: Int) {
    Other(0),
    Analgesics(1),
    Antibiotics(2),
    Anticoagulants_And_Thrombolytics(3),
    Antidepressants(4),
    Antivirals(5),
    Barbiturates(6),
    Beta_Blockers(7),
    Corticosteroids(8),
    Cough_Suppressants(9),
    Expectorant(10),
    Hormones(11),
    Sedatives(12),
    Vitamins(13)
}

// TODO think about how to convert it
data class MyMedicineType(val type: MedicineType){
    override fun toString(): String {
        return type.type
    }
}
