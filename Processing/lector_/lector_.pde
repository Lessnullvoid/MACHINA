//Processing code

import processing.serial.*;

Serial myPort;  // Create object from Serial class
int val;      // Data received from the serial port

void setup()
{
  size(200, 200);
  println(Serial.list());  //list all the available serial ports
  //replace the 8 in the next line with your serial port number from the list above.  
  String portName = Serial.list()[6]; //My device is using "port 8" on my laptop
  myPort = new Serial(this, Serial.list()[6], 115200); //115200 is the data rate associated with this Bluetooth module

}


void draw()
{
  if ( myPort.available() > 0) {  // If data is available,
    String myString = myPort.readStringUntil('\n');  //reads serial port until end of line
    if (myString != null) { //checks if myString is null
      myString = trim(myString);
      val = int(myString);  //converts string to integer
      background(255);             // Set background to white
      if (val < 150) {              // If the serial value is 0,
        fill(0);                   // set fill to black
      }
      else {                       // If the serial value is not 0,
        fill(204);                 // set fill to light gray
      }
      rect(50, 50, 100, 100);
    }
  }
}
 
