package com.minjalidze.anonimousvotes.ui.theme

import androidx.compose.ui.graphics.Color

class GradientStorage {
    companion object {
        var gradients = listOf(
            Gradient.OceanBlue,
            Gradient.Sanguine,
            Gradient.LusciousLime,
            Gradient.PurpleLake,
            Gradient.Piglet,
            Gradient.Kashmir,
            Gradient.GreenBeach,
            Gradient.BloodyMary
        )
    }
}

fun getGradient(id: Int) : Gradient {
    return GradientStorage.gradients.first {
        it.id == id
    }
}

sealed class Gradient(var id: Int, var title: String, var firstColor: Color, var secondColor: Color) {
    data object OceanBlue       :    Gradient(0, "Синий океан", gradient_OceanBlue_first, gradient_OceanBlue_second)
    data object Sanguine        :    Gradient(1, "Сангвиник", gradient_Sanguine_first, gradient_Sanguine_second)
    data object LusciousLime    :    Gradient(2, "Сочный лайм", gradient_LusciousLime_first, gradient_LusciousLime_second)
    data object PurpleLake      :    Gradient(3, "Пурпурное озеро", gradient_PurpleLake_first, gradient_PurpleLake_second)
    data object Piglet          :    Gradient(4, "Поросенок", gradient_Piglet_first, gradient_Piglet_second)
    data object Kashmir         :    Gradient(5, "Кашмир", gradient_Kashmir_first, gradient_Kashmir_second)
    data object GreenBeach      :    Gradient(6, "Зеленый пляж", gradient_GreenBeach_first, gradient_GreenBeach_second)
    data object BloodyMary      :    Gradient(7, "Кровавая Мэри", gradient_BloodyMary_first, gradient_BloodyMary_second)
}
