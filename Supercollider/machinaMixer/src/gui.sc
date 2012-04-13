//-------------------------
//gui:
w = Window.new("machinaMixer",Rect(0,0,450,700));
View.globalKeyDownAction={arg view,char,modifiers,unicode,keycode;
	keycode.postln;
	
	switch(keycode,
	6, {a.moduleButtons(0)},
	7, {a.moduleButtons(1)},
	8, {a.moduleButtons(2)},
	9, {a.moduleButtons(3)},
	12,{g.list[0].valueAction=g.list[0].value-1;},
	0, {g.list[0].valueAction=g.list[0].value+1;},
	13,{g.list[1].valueAction=g.list[1].value-1;},
	1, {g.list[1].valueAction=g.list[1].value+1;},
	18,{a.selectSampler(0)},
	19,{a.selectSampler(1)});

};

w.view.background=c.fondo;

g.window=w;
w.onClose={
	z.sampler.do{|synth|synth.free;};
	k.p.close;
};
g.list=List.new;
g.playButtons=List.new;

g.space=( (g.window.view.bounds.width)/g.noLists );
g.width=( (g.window.view.bounds.width)/g.noLists ) - (2*g.padding);

2.do{|i|
	
	b = Button(w,Rect(
		g.padding+(g.space*i),
		g.padding,
		40,40)).canFocus_(false);

	b.states=[
		[">",c.texto,c.botonAEstadoA],
		["||",c.texto,c.botonAEstadoB]];

	b.action={|button|
		a.playButtons(button,i);
	};


	g.playButtons.add(b);

	l = ListView(w,Rect(
		g.padding+(g.space*i),
		50,
		g.width,
		g.width-50)
	).canFocus_(false);
	l.hiliteColor=c.botonAEstadoB;
	l.selectedStringColor=c.texto;
	l.stringColor=c.textoB;
	l.action={|list| a.listView(list,i); };

	l.items=Array.fill(v.samples.size,{arg i; v.samples[i]});

   g.list.add(l);

};




g.width= ( (g.window.view.bounds.width)*g.smallKnobSize )-g.padding;

g.volKnobs=List.new;

g.moduleKnobs=List.new;

g.moduleKnobs.add(Array.newClear(3));
2.do{|i|
	b=Knob(w,Rect(
		g.padding		+				(i*(g.window.view.bounds.width-(g.width)-(2*g.padding))),
		200+(g.width/2),
		g.width,g.width)
	).canFocus_(false);	
	b.value=0.5;
	b.color=c.knobColor;
	b.action={|knob| a.volumeKnob(knob,i);};
	g.volKnobs.add(b);
	
	g.moduleKnobs[0][i*2]=b;

	if(i==0) {
	
		b=Slider(w,Rect(
			g.padding+g.width,
			200+(g.width/2),
			g.window.view.bounds.width-(2*g.width)-(g.padding*2),
			50)).canFocus_(false);
		b.value=0.5;
		b.action={|slider|a.slider(slider)};
		b.background=c.amarillo;
		b.knobColor=c.negro;
		g.slider=b;

		g.moduleKnobs[0][1]=b;
	};
};


g.width= ( (g.window.view.bounds.width)/(g.noModules-1) )-g.padding;
(g.noModules-1).do{|i|


g.subview = VLayoutView(w, Rect(g.width/2+(i*g.width),g.window.view.bounds.height/2.25,30,250) ).canFocus_(false);

g.moduleKnobs.add(
	Array.fill( (g.noModules-1),
	{ arg j;
		Knob( g.subview, Rect(0,0,30,30) ).value_(0.5).action_({|knob| a.moduleKnob(knob,i,j) }).color_(c.knobColor).canFocus_(false)

	})
);

};


g.selectSampler=List.new;

//synthbuttons
g.knobwidth= ( (g.window.view.bounds.width)/g.noKnobs ) - g.padding;
g.width= ( (g.window.view.bounds.width)/2 ) - g.padding;
2.do{|i|
	b=Button(w,Rect(g.padding+(g.width*i),
			g.window.view.bounds.height-(2*g.knobwidth),
			g.width,
			(0.2*g.knobwidth))).canFocus_(false);
	b.states=[
		["sampler"++i,c.texto,c.botonAEstadoA],
		["sampler"++i,c.texto,c.botonAEstadoB]];

	b.action={|button|
		a.selectSampler(i);
	};
	b.value=(i+1)%2;

	g.selectSampler.add(b);

};


g.width= ( (g.window.view.bounds.width)/g.noKnobs ) - g.padding;

g.inKnobs=List.new;

g.noKnobs.do{|i|
	b=Knob(w,Rect(
	g.padding+(i*(g.width+(g.padding/g.noKnobs))),
	g.window.view.bounds.height-(1.75*g.width),
	g.width,g.width)).canFocus_(false);
	b.value=0.5;
	b.action={|knob|a.inKnob(knob,i)};
	b.color=c.knobColor;

	g.inKnobs.add(b);
};





g.width= ( (w.view.bounds.width)/g.noButtons ) - g.padding;

g.labels=["mixer","eq","fx","fx2"];
g.moduleButtons=List.new;
g.noButtons.do{|i|
	b=Button(w,Rect(
		g.padding+(i*(g.width+g.padding)),
		g.window.view.bounds.height-g.width,
		g.width,g.width)
	).canFocus_(false);
	b.states=[
		[g.labels[i],c.texto,c.botonAEstadoA],
		[g.labels[i],c.texto,c.botonAEstadoB]];

	b.action={|button|
		a.moduleButtons(i);
	};

	g.moduleButtons.add(b);
};


w.front;

