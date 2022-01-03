<#import "util.ftl" as util/>


<#assign complex_beer_map = 
     {
          "name": "Imperial Stout",
          "description": "Tasty Stout Beer",
          "food_pairing": [
               "Salmon",
               "Pizza with Taleggio"
           ]
     } >

<div> ${complex_beer_map.name} </div>
<div> ${complex_beer_map.description} </div>
<#list complex_beer_map.food_pairing as food_pairing>
     <div> ${food_pairing} </div>
</#list>
