# color-data-collector
Data collection tool for color recognition based on EEG signals.

The tool takes a .csv file as input with the format `r, g, b, duration`. It will output a .csv file with the color associated with each sample assuming a 50 Hz. sampling rate.

Example .csv available at https://github.com/victorjmarin/color-data-collector/blob/master/random-slides.csv.

Can run the tool with the following command:
`java -jar colors.jar random-slides.csv`
