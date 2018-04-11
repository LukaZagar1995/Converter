# Converter

## Main Activity
Switch between activities <br />
<**[Button name]**>.setOnClickListener{<br />
            val intent = Intent (this, <**[Name of activity you want to go to]**>::class.java)<br />
            intent.putExtra(KEY_BUTTON_IDENTIFIER, <**[Button indentifier]**>)<br />
            startActivity(intent) } <br />
            
## Screenshots
<img src="https://github.com/LukaZagar1995/Converter/blob/master/IzgledAplikacije/MainMenu.jpg" width="200" height="350"> <img src="https://github.com/LukaZagar1995/Converter/blob/master/IzgledAplikacije/TemperatureConversion.jpg" width="200" height="350"> <img src="https://github.com/LukaZagar1995/Converter/blob/master/IzgledAplikacije/Result.jpg" width="200" height="350"> <img src="https://github.com/LukaZagar1995/Converter/blob/master/IzgledAplikacije/DistanceConversion.jpg" width="200" height="350">
