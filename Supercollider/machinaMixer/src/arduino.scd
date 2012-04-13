
k.noSensors=3;
k.thresHold=30;
k.storedVals=10;
k.valArray=Array.fill(k.noSensors,{Array.fill(k.storedVals,0)});
k.valCounter=0;

k.checkTouch={arg e,i;
	var touching=true;	
	k.valArray[i].size.do{arg j;
		k.valArray[i].size.do{arg l;
			if( (k.valArray[i][j]-k.valArray[i][l]).abs>k.thresHold ) {
				touching=false;
			};
			
		};
	};
	touching;
};

k.a = Routine{
		inf.do{
			k.valCounter=(k.valCounter+1)%k.storedVals;
			
			k.p.iterate;
				
			k.noSensors.do{arg i;
				k.valArray[i][k.valCounter]=k.p.analog[i].value;
				//k.p.analog[i].value.post; "  ".post;
				if(k.valCounter==(k.valArray[i].size-1)){
					if(k.checkTouch(i)){
						("on: "++i.asString++" val: "++k.valArray[i][0]).postln;
						g.inKnobs[i].valueAction=k.p.analog[i].value/460;
					
					}	
					{
				//		"0".post; "   ".post;
					}
				};
				
				
			};
//			0.05.wait;
//			"".postln;
		}
};



Routine{1.do{


k.d = SerialPort.devices[3]; //select correct index for serial port
k.p = SCPyduino.new(k.d,57600);
4.wait;
k.p.analog[0].active_(1); //activates polling for analog pin 0 on the microcontroller
k.p.analog[1].active_(1); //activates polling for analog pin 0 on the microcontroller
k.p.analog[2].active_(1); //activates polling for analog pin 0 on the microcontroller


AppClock.play(k.a);


}}.play;