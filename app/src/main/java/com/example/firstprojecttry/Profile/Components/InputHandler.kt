package com.example.firstprojecttry.Profile.Components

import com.example.firstprojecttry.Logic.DescriptionType


fun handleStringInput(
    fieldBody: String,
    changeError: (Boolean) -> (Unit) = {},
    type: DescriptionType,
    restrictionSize: Int,
    symbolsCount: Int,
    onError : (Boolean) -> (Unit) = {}
) {
    changeError(false);
    onError(true);
    if (type == DescriptionType.STRING) {
        changeError(symbolsCount > restrictionSize)
        onError(symbolsCount <= restrictionSize);
    } else {
        if ( fieldBody  == "" || (fieldBody.length > 1 && fieldBody[0] == '0')) {
            changeError(true);
            onError(false);

        }
        try{
            val x =  fieldBody.toInt()
            if (x > restrictionSize) {
                changeError(true);
                onError(false);

            }
        } catch (e : Exception) {
            changeError(true);
            onError(false);
        }
    }
}
