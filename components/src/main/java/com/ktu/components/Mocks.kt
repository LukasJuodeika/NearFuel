package com.ktu.components

import com.ktu.components.objects.GasStation

object Mocks {

    public fun getGasStations(): List<GasStation> {
        return arrayListOf(
            GasStation("Statoil", "1.0", "benzas", "", "", 122.2),
            GasStation("Circle", "1.2", "dyzelis", "", "", 122.2),
            GasStation("K", "1.4", "salera", "", "", 122.2),
            GasStation("Neste", "1.5", "benzas", "", "", 122.2),
            GasStation("a", "1.6", "dujos", "", "", 122.2)
        )

    }

}