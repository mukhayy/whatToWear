package weather;

public class Temperature {

        public static String value;
        private String max;
        private String min;
        private String unit;
        public Temperature(){}

        public Temperature(String value, String min, String max, String unit)
        {
            setValue(value);
            setMin(min);
            setMax(max);
            setUnit(unit);
        }

        public String getValue()
        {
            return value;
        }

        public void setValue(String value)
        {
            this.value = value;
        }

        public String getMax()
        {
            return max;
        }

        public void setMax(String max)
        {
            this.max = max;
        }

        public String getMin()
        {
            return min;
        }

        public void setMin(String min)
        {
            this.min = min;
        }

        public String getUnit()
        {
            return unit;
        }

        public void setUnit(String unit)
        {
            this.unit = unit;
        }


        public String temprature()
        {
            return getValue()+"°C";}
    // + "\nMin: "+getMin()+"\nMax: "+ getMax();
        }


