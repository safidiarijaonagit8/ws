package web.service.webservicefrontofficeetmobile.exception;
import java.util.Date;

public class ErrorDetails 
{
    private Date timesTamp;
    private String message;
    private String details;
    
    public void setTimesTamp(Date timesTamp)
    {
        this.timesTamp = timesTamp;
    }

    public Date getTimestamp()
    {
        return this.timesTamp;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return this.message;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public String getDetails()
    {
        return this.details;
    }

    public ErrorDetails()
    {

    }

    public ErrorDetails(Date timesTDate, String message, String details)
    {
        this.setTimesTamp(timesTamp);
        this.setMessage(message);
        this.setDetails(details);
    }
}
