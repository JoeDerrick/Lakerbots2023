package frc.robot.subsystems;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class leds extends SubsystemBase{
    public AddressableLED m_led;
    private AddressableLEDBuffer m_ledBuffer;
    
    public leds(){
        m_led = new AddressableLED(9);
        m_ledBuffer = new AddressableLEDBuffer(60); //CHANGE THE LENGTH VALUE TO THE ACTUAL LENGHT
        m_led.setLength(m_ledBuffer.getLength());

    
        m_led.setData(m_ledBuffer);
        m_led.start();

    }
    public void yellow(){
        for(var i = 0; i < m_ledBuffer.getLength(); i++){
            m_ledBuffer.setRGB(i, 255, 255, 0);
        }
    }
    public void purple(){
        for(var i = 0;i < m_ledBuffer.getLength(); i++){
            m_ledBuffer.setRGB(i, 255, 0, 255);
        }
    }
    public void green(){
        for(var i = 0;i < m_ledBuffer.getLength(); i++){
            m_ledBuffer.setRGB(i, 0, 255, 0);
        }
    }
    public void spiritColors(){
        for(var i = 0;i < m_ledBuffer.getLength(); i++){
            if(i % 2 == 0){
            m_ledBuffer.setRGB(i, 255, 255, 255); //white
            }
            else{
                m_ledBuffer.setRGB(i, 0, 0, 255); //blue
            }
        }
    }



    @Override
    public void periodic(){
        m_led.setData(m_ledBuffer);
    }

}
