a.playButtons={|e,button,i|
i.postln;
	if(button.value==0) {
		z.sampler[i].free;
		z.sampler[i]=nil;
	};		
	if(button.value==1) {
		if(z.sampler[i]!=nil) { z.sampler[i].free }
		{
			Buffer.readChannel(s,v.samplesPath++g.list[i].item,
				channels:[0,1],
			action:{|buf| z.addSynth(buf,i) });
		};
	};		


};



a.listView={|env,list,i|

	if(z.sampler[i]!=nil) { 
		z.sampler[i].free; z.sampler[i]=nil;
		Buffer.readChannel(s,v.samplesPath++list.item,
			channels:[0,1],
		action:{|buf| z.addSynth(buf,i)});
		g.playButtons[i].value=1;

	}
	{
		g.playButtons[i].value=1;
		Buffer.readChannel(s,v.samplesPath++list.item,
			channels:[0,1],
		action:{|buf| z.addSynth(buf,i);  });
	};
};		


a.selectSampler={|e,i|
	g.selectSampler[i].value=1;
	g.selectSampler[(i+1)%2].value=0;	
	m.targetSampler=i;
};


a.volumeKnob={|env,knob,channel|
	knob.postln;
};

a.slider={|env,slider|slider.value.postln};

a.moduleKnob={|env,knob,i,j|[knob.value,i,j].postln};

a.inKnob={|env,knob,i| 
	g.moduleKnobs[m.currentModule][i].valueAction=knob.value;

	a.knobFunctions[m.currentModule][i].(knob.value);

};





a.moduleButtons={|e,i|
	m.currentModule=i;
	g.moduleButtons[i].value=1;
	g.noButtons.do{|j|
		if(i!=j){ g.moduleButtons[j].value=0; }
		
	};
};

a.knobFunctions=
[
[	// volume:
	{|val|	z.sampler[0].set(\gain,val); z.gains[0]=val; },
	{|val|
		z.sampler[0].set(\amp,1-val);
		z.amps[0]=1-val;
		z.sampler[1].set(\amp,val);
		z.amps[1]=val;
	},
	{|val|	z.sampler[1].set(\gain,val); z.gains[1]=val;}

],
[	// eq;
		
	{|val|	z.sampler[m.targetSampler].set(\eqhidb,(val-0.5)*12); },
	{|val|	z.sampler[m.targetSampler].set(\eqmiddb,(val-0.5)*12); },
	{|val|	z.sampler[m.targetSampler].set(\eqlodb,(val-0.5)*12); },

],
[	// fx1
	{|val|	z.sampler[m.targetSampler].set(\ffreq,val*12000); },
	{|val|	z.sampler[m.targetSampler].set(\fq,1-val); },
	{|val|	z.sampler[m.targetSampler].set(\fq,1-val); }
],
[	// fx2
	{|val|	z.sampler[m.targetSampler].set(\rdamp,val); },
	{|val|	z.sampler[m.targetSampler].set(\rtime,val); },
	{|val|	z.sampler[m.targetSampler].set(\rmix,val); }
],
];

//a.knobFunctions[0].add({|val|	z.sampler[0].set(\amp,val); });
