 //codigo de arduino para envio de datos bluetooth
 
 int analogoPin = 0;    // Analog input pin 
 int val = 0;   // value read from the analog sensor
 
 void turn_all_off()            
{                              
  int i; 
  for(i=1; i<20; i++)		
  { 
    digitalWrite(i, LOW); 
  } 
} 


 void setup() {
 Serial.begin(115200);
}

 void loop() {
  val = analogRead(analogoPin);
   Serial.println(val);
   Serial.flush();
   delay(200);
}
