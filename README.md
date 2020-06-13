# NationalWeatherService
National Weather Service

This is client for the National Weather Service API and this will get the forecast for particular location based on co-ordinates for 5 days.

1. Clone repository and navigate to NationalWeatherService folder.
  ``` bash
    git clone https://github.com/braynpop/NationalWeatherService.git
  ```
2. Run the below command to build the jar with dependencies 
    ``` bash
   mvn package
   ```
3. Run below java command and provide the require arguments.
   ```bash  
     java -jar com.nws.forecast-0.0.1-SNAPSHOT-jar-with-dependencies.jar 
   ```
     Enter latitude, longitude
      ``` bash
      39.7456, -97.0892
      ```

Sample Output:
  ```bash
  ForecastDetailResponse {latitude:39.7456, longitude:-97.0892, detailedForecast:[{2020-06-13: [18:00:00 to 11:59:59 : Mostly clear, with a low around 71. Southeast wind around 15 mph, with gusts as high as 25 mph.]}, {2020-06-14: [00:00:00 to 06:00:00 : Mostly clear, with a low around 71. Southeast wind around 15 mph, with gusts as high as 25 mph., 06:00:00 to 18:00:00 : Sunny, with a high near 97. South wind 15 to 20 mph, with gusts as high as 30 mph., 18:00:00 to 11:59:59 : Partly cloudy, with a low around 72. South wind 15 to 20 mph, with gusts as high as 30 mph.]}, {2020-06-15: [00:00:00 to 06:00:00 : Partly cloudy, with a low around 72. South wind 15 to 20 mph, with gusts as high as 30 mph., 06:00:00 to 18:00:00 : Mostly sunny, with a high near 95. South wind 10 to 15 mph, with gusts as high as 30 mph., 18:00:00 to 11:59:59 : Mostly clear, with a low around 71. South wind 10 to 15 mph, with gusts as high as 25 mph.]}, {2020-06-16: [00:00:00 to 06:00:00 : Mostly clear, with a low around 71. South wind 10 to 15 mph, with gusts as high as 25 mph., 06:00:00 to 18:00:00 : Sunny, with a high near 96. South wind 10 to 15 mph, with gusts as high as 30 mph., 18:00:00 to 11:59:59 : Mostly clear, with a low around 74. South wind around 15 mph, with gusts as high as 30 mph.]}, {2020-06-17: [00:00:00 to 06:00:00 : Mostly clear, with a low around 74. South wind around 15 mph, with gusts as high as 30 mph., 06:00:00 to 18:00:00 : Sunny, with a high near 96. South wind around 15 mph, with gusts as high as 30 mph., 18:00:00 to 11:59:59 : A slight chance of showers and thunderstorms after 1am. Partly cloudy, with a low around 72. South wind 10 to 15 mph, with gusts as high as 25 mph. Chance of precipitation is 20%.]}]}
  ```
