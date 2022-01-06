<#import "util.ftl" as util/>
<#import "/freemarker/util.ftl" as commonUtil/>
<#assign inflearn="https://www.inflearn.com/pages/6th-birthday/-20211220" />

<@util.inputData  />


<p>예시 uri 입력 / 갯수 입력</p>
입력
URI : ${inflearn}  NUMBER : 2  
<@commonUtil.pressEnter count=2 />
출력 : 
${util.uriTest (inflearn, 2)}

<p>================================================</p>

<@util.parameter />

<#assign complex_beer_map = 
	{ 	
		"name": "Imperial Stout", 
		"description": "Tasty Stout Beer",
		"food_pairing": [
			"Salmon",   
			"Pizza with Taleggio"
		]
	} >

<script>
	var beerMap = ${jsonFactoryUtil.looseSerializeDeep(complex_beer_map)};
	console.log(beerMap);
</script>






