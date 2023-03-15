from bs4 import BeautifulSoup
import requests
from tkinter import *

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3'}

def getWeather(cityName):
    cityName = cityName.replace(" ","+")
    myRes = requests.get(f"https://www.google.com/search?q={cityName}&oq={cityName}", headers=headers)
    soup = BeautifulSoup(myRes.text, 'html.parser')
    location = soup.select("#wob_loc")[0].getText()
    time = soup.select('#wob_dts')[0].getText()
    info = soup.select("#wob_dc")[0].getText()
    temp = soup.select("#wob_tm")[0].getText()
    return [location,time, info,temp]

def update_weather(city):
    reqAns = getWeather(city + " weather")
    weatherFormat = "Location: {0}\nTime: {1}\nInfo: {2}\nTemp: {3} F".format(city,reqAns[1],reqAns[2],reqAns[3])
    result.configure(text=weatherFormat)

def main():
    root = Tk()
    root.title("Weather App")

    cityLabel = Label(root, text="Please enter the city you would like to look at!")
    cityEnter = Entry(root, width=10)
    global result
    result = Label(root,text="")
    btn = Button(root, text="Get Weather", command= lambda : update_weather(cityEnter.get()))

    root.geometry("500x200")

    cityLabel.grid()
    cityEnter.grid(column=1, row=0)
    btn.grid(column=2, row=0)
    result.grid(column=0, row=1)
    root.mainloop()
if __name__ == "__main__":
    main()