package com.herdal.dummyshoppingcenter.utils.ext

import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import java.text.NumberFormat

fun ProductUiModel.getFormattedPrice(): String =
    NumberFormat.getCurrencyInstance().format(this.price)