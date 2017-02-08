POST

URL : http://localhost:3000/shops

Content-Type	application/json;charset=UTF-8

{
  "shopName":"Home Town",
   "shopAddress":
         {
           "number":1600,
           "postCode":700156
         }
}



URL : http://localhost:3000/shops

Content-Type	application/json;charset=UTF-8

{
  "shopName":"Axis Mall",
   "shopAddress":
         {
           "number":1600,
           "postCode":700156
         }
}


URL : http://localhost:3000/shops

Content-Type	application/json;charset=UTF-8

{
  "shopName":"Eco Park",
   "shopAddress":
         {
           "number":1600,
           "postCode":700156
         }
}


========================================================================================

GET

https://maps.googleapis.com/maps/api/geocode/json?address=New+Town+Bus+Stop,700156&key=AIzaSyAelw4voJokY89NBhPX1NPus5_nQujT-bQ

"location" : {
               "lat" : 22.5818985,
               "lng" : 88.4529769
            }


http://localhost:3000/shops?customerLatitude=22.5818985&customerLongitude=88.4529769




[Shop [shopName=Home Town, shopAddress=ShopAddress [number=1600, postCode=700156], shopLongitude=88.4585979, shopLatitude=22.5830465], Shop [shopName=Axis Mall, shopAddress=ShopAddress [number=1600, postCode=700156], shopLongitude=88.45989399999999, shopLatitude=22.5795843], Shop [shopName=Eco Park, shopAddress=ShopAddress [number=1600, postCode=700156], shopLongitude=88.4707215, shopLatitude=22.5959907]]


Home Town --> distance from New Town Bus Stop = 0.5910253182937809
Axis Mall --> distance from New Town Bus Stop = 0.7553294270782349
Eco Park --> distance from New Town Bus Stop = 2.402834783781203


