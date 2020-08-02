package weather;

public class Weather{

    public String value;

    public Weather(){}

    public Weather(String value)
    {
        super();
        setValue(value);
    }

    /**
     * Pressure object constructor receiving three parameters
     *
     * @param value
     *            Describes the current weather
    /
    /**
     * A get property for the value of the current weather condition
     *
     * @return a string that describes the value of the current weather
     *         condition
     */
    public String getValue()
    {
        return value;
    }

    /**
     * A set property for the value of the current weather condition
     *
     * @param value
     */
    public void setValue(String value)
    {
        this.value = value;
    }


    @Override
    public String toString()
    {
        return  getValue();
    }



}

