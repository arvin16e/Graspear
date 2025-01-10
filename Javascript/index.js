
//  alert('oops');
  console.log(12345);
 let input=prompt();
 
 console.log("your bill amount is : "+input);

 num=prompt();
 try{
    if(isNaN(num)){
         throw "Enter numbers only"
        
    }else{
        console.log(num**2);
    }
    let json1=123
    let json2=[
   {
        "name":"arvin",
        "age":23
    },
    {
         "name":"vijay",
        "age":50
    },
    {
         "name":"kumar",
        "age":50
    }]
let str=JSON.stringify(json2)
 let parsed=JSON.parse(str)
 console.log(parsed);
 console.log(parsed[0].name);
 console.log(parsed[0].age);
 console.log(parsed[1]);
   
 }catch(error){
    console.log(error);
 }

 
