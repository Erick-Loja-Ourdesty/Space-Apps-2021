char c;
int LED47=47;
int LED31=31;
int LED33=33;
void setup() {
  Serial.begin(9600);
  pinMode(2,OUTPUT); //Pin para control del Reset del modulo HC05
  digitalWrite(13, LOW);
  delay(200);
  digitalWrite(13, HIGH);
  // El modulo HC-05 tiene por defecto el Baud rate a 38400 en el modo AT 
  Serial1.begin(38400);  
  pinMode(LED47, OUTPUT);
  digitalWrite(LED47,HIGH);
  digitalWrite(LED31,HIGH);
  digitalWrite(LED33,HIGH);
  delay(2000);
  digitalWrite(LED47,LOW);
  digitalWrite(LED31,LOW);
  digitalWrite(LED33,LOW);
  delay(2000);
  digitalWrite(LED47,HIGH);
  digitalWrite(LED31,HIGH);
  digitalWrite(LED33,HIGH);
}
 
void loop(){ 
    // Esperar respuesta del modulo HC-05 y se envia a el monitor serial
    if (Serial1.available()> 0)
    {  
        c = Serial1.read();
        delay(5);
        Serial.write(c);
        Serial.println(int(c));  
        if(int(c)==-1){
          digitalWrite(LED47,LOW);
        }
        if(int(c)==-4){
          digitalWrite(LED47,HIGH);
        }
        if(int(c)==-2){
          digitalWrite(LED31,LOW);
        }
        if(int(c)==-3){
          digitalWrite(LED31,HIGH);
        }
        if(int(c)==-8){
          digitalWrite(LED33,HIGH);
        }
        if(int(c)==-10){
          digitalWrite(LED33,LOW);
        }
        
    }
    // Esperar codigos enviados por el monitor serial y se envian a el modulo HC-05
    if (Serial.available()> 0)
    {
        c =  Serial.read();
        delay(5);
        Serial1.write(c);
        Serial1.println(String(c));  
    }
}
