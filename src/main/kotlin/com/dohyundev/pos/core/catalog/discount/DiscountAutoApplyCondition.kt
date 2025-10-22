package com.dohyundev.pos.core.catalog.discount

import com.dohyundev.common.entity.Activatable
import com.dohyundev.common.entity.DisplayOrderable
import com.dohyundev.common.entity.TsidBaseEntity
import jakarta.persistence.Entity

@Entity
class DiscountAutoApplyCondition(

): TsidBaseEntity(), DisplayOrderable, Activatable {
}