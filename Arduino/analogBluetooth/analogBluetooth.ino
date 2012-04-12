 //codigo de arduino para envio de datos bluetooth
 
 int analogoPin = 0;    // Analog input pin 
 int val = 0;   // value read from the analog sensor

 void setup() {
 Serial.begin(115200);
}

 void loop() {
  val = analogRead(photocellPin);
   Serial.println(val);
   Serial.flush();
   delay(200);
}
