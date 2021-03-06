import java.util.*;

public class ContactList
{
    /**
        This program stores and displays contacts which are categorized as
        either personal or business.
    */
    public static void main(String args[])
    {
        boolean done = false;
        Scanner in = new Scanner(System.in);
        ArrayList<String> options = new ArrayList<String>(); // main menu options
        options.add("Add a business contact");
        options.add("Add a personal contact");
        options.add("Display contacts");
        options.add("Quit");
        Menu mainMenu = new Menu(options);
        TreeSet<Contact> bContacts = new TreeSet<Contact>(); // business contacts list
        TreeSet<Contact> pContacts = new TreeSet<Contact>(); // personal contacts list
        Iterator<Contact> itr;
        String err = "Error: invalid selection.";
        while(!done)
        {
            System.out.println();
            for(int i = 0; i < mainMenu.getOptions().size(); i++)
            {
                System.out.printf("%s. %s\n", i + 1, mainMenu.getOptions().get(i));
            }
            System.out.printf("\nChoose an option: ");
            if(in.hasNextInt())
            {
                int selection = in.nextInt();
                if(selection > 0 && selection <= mainMenu.getOptions().size())
                {
                    if(selection == 4) // quit
                    {
                        done = true;
                    }
                    else if(selection == 3) // display contacts
                    {
                        ArrayList<String> displayMenuOpt = new ArrayList<String>();
                        displayMenuOpt.add("Display business contacts.");
                        displayMenuOpt.add("Display personal contacts.");
                        displayMenuOpt.add("Return to main menu.");
                        Menu displayMenu = new Menu(displayMenuOpt);
                        System.out.println();
                        boolean displaying = true;
                        while(displaying)
                        {
                            System.out.println();
                            for(int i = 0; i < displayMenu.getOptions().size(); i++)
                            {
                                System.out.printf("%s. %s\n", i + 1, displayMenu.getOptions().get(i));
                            }
                            System.out.printf("\nChoose an option: ");
                            if(in.hasNextInt())
                            {
                                int choice = in.nextInt();
                                System.out.println();
                                if(choice == 1) // display business contacts
                                {
                                    if(bContacts.size() > 0)
                                    {
                                        boolean detail = true;
                                        while(detail)
                                        {
                                            System.out.println("=====================");
                                            System.out.println("| Business Contacts |");
                                            System.out.println("=====================");
                                            itr = bContacts.iterator();
                                            displayShortList(itr);
                                            System.out.println();
                                            System.out.print("Select a contact to view details (m to return to previous menu): ");
                                            if(in.hasNext())
                                            {
                                                String input = in.next();
                                                if(input.toLowerCase().equals("m")) // return to previous menu
                                                {
                                                    detail = false;
                                                }
                                                else
                                                {
                                                    Integer temp = 0;
                                                    try
                                                    {
                                                        temp = temp.parseInt(input);
                                                    }
                                                    catch(NumberFormatException e)
                                                    {
                                                        System.out.println(err);
                                                    }
                                                    if(temp > 0 && temp <= bContacts.size())
                                                    {
                                                        BusinessContact[] bDetail = bContacts.toArray(new BusinessContact[bContacts.size()]);
                                                        ArrayList<String> cDetail = bDetail[temp - 1].getContact();
                                                        System.out.println();
                                                        for(int i = 0; i < cDetail.size(); i++)
                                                        {
                                                            System.out.println(cDetail.get(i));
                                                        }
                                                        System.out.println();
                                                    }
                                                    else
                                                    {
                                                        System.out.println(err);
                                                    }
                                                }
                                            }
                                            else
                                            {
                                                System.out.println(err);
                                            }
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("No business contacts found.  Please enter business contacts.\n");
                                    }
                                }
                                else if(choice == 2) // display personal contacts
                                {
                                    if(pContacts.size() > 0)
                                    {
                                        boolean detail = true;
                                        while(detail)
                                        {
                                            System.out.println("=====================");
                                            System.out.println("| Personal Contacts |");
                                            System.out.println("=====================");
                                            itr = pContacts.iterator();
                                            displayShortList(itr);
                                            System.out.println();
                                            System.out.print("Select a contact to view details (m to return to previous menu): ");
                                            if(in.hasNext())
                                            {
                                                String input = in.next();
                                                if(input.toLowerCase().equals("m")) // return to previous menu
                                                {
                                                    detail = false;
                                                }
                                                else
                                                {
                                                    Integer temp = 0;
                                                    try
                                                    {
                                                        temp = temp.parseInt(input);
                                                    }
                                                    catch(NumberFormatException e)
                                                    {
                                                        System.out.println(err);
                                                    }
                                                    if(temp > 0 && temp <= pContacts.size())
                                                    {
                                                        PersonalContact[] pDetail = pContacts.toArray(new PersonalContact[pContacts.size()]);
                                                        ArrayList<String> cDetail = pDetail[temp - 1].getContact();
                                                        System.out.println();
                                                        for(int i = 0; i < cDetail.size(); i++)
                                                        {
                                                            System.out.println(cDetail.get(i));
                                                        }
                                                        System.out.println();
                                                    }
                                                    else
                                                    {
                                                        System.out.println(err);
                                                    }
                                                }
                                            }
                                            else
                                            {
                                                System.out.println(err);
                                            }
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("No personal contacts found.  Please enter personal contacts.\n");
                                    }
                                }
                                else if(choice == 3) // return to main
                                {
                                    displaying = false;
                                }
                            }
                            else
                            {
                                System.out.println(err);
								in.next();
                            }
                        }
                    }
                    else if(selection == 2) // add personal contact
                    {
                        boolean complete = false;
                        String input = "";
                        while(!complete)
                        {
                            ArrayList<String> info = new ArrayList<String>();
                            in.nextLine(); // consume newline character from main menu
                            System.out.println();
                            System.out.println("Create a new personal contact:");
                            System.out.print("Enter contact first name> ");
                            info.add(in.nextLine());
                            System.out.print("Enter contact last name> ");
                            info.add(in.nextLine());
                            System.out.println("Enter contact address information:");
                            System.out.print("Enter building number> ");
                            info.add(in.nextLine());
                            System.out.print("Enter cardinal direction \"N, S, SE...\"> ");
                            info.add(in.nextLine());
                            System.out.print("Enter street name> ");
                            info.add(in.nextLine());
                            System.out.print("Enter street tag \"AVE, BLVD...\"> ");
                            info.add(in.nextLine());
                            System.out.print("Enter city> ");
                            info.add(in.nextLine());
                            System.out.print("Enter state code \"AK, AL...\"> ");
                            info.add(in.nextLine());
                            System.out.print("Enter zip code> ");
                            info.add(in.nextLine());
                            System.out.print("Enter contact ten-digit phone number \"5555555555\"> ");
                            info.add(in.nextLine());
                            System.out.print("Enter contact email address> ");
                            info.add(in.nextLine());
                            System.out.print("Enter contact date of birth \"YYYYMMDD\"> ");
                            info.add(in.nextLine());
                            try
                            {
                                pContacts.add
                                (
                                    (
                                        new PersonalContact
                                        (
                                            info.get(0), info.get(1), info.get(2), info.get(3), info.get(4),
                                            info.get(5), info.get(6), info.get(7), info.get(8), info.get(9),
                                            info.get(10), info.get(11)
                                        )
                                    )
                                );
                            }
                            catch(IllegalArgumentException e)
                            {
                                System.out.println(e);
                            }
                            System.out.print("Create another personal contact (Y or N): ");
                            if(in.hasNext()) 
                            {
                                input = in.next();
                            }
                            else
                            {
                                System.out.println("Invalid input");
                            }
                            if(input.toLowerCase().equals("n"))
                            {
                                complete = true;
                            }
                        }
                    }
                    else if(selection == 1) // add business contact
                    {
                        boolean complete = false;
                        while(!complete)
                        {
                            ArrayList<String> info = new ArrayList<String>();
                            in.nextLine(); // consume newline character from main menu
                            System.out.println();
                            System.out.println("Create a new business contact:");
                            System.out.print("Enter contact first name> ");
                            info.add(in.nextLine());
                            System.out.print("Enter contact last name> ");
                            info.add(in.nextLine());
                            System.out.println("Enter contact address information:");
                            System.out.print("Enter building number> ");
                            info.add(in.nextLine());
                            System.out.print("Enter cardinal direction \"N, S, SE...\"> ");
                            info.add(in.nextLine());
                            System.out.print("Enter street name> ");
                            info.add(in.nextLine());
                            System.out.print("Enter street tag \"AVE, BLVD...\"> ");
                            info.add(in.nextLine());
                            System.out.print("Enter city> ");
                            info.add(in.nextLine());
                            System.out.print("Enter state code \"AK, AL...\"> ");
                            info.add(in.nextLine());
                            System.out.print("Enter zip code> ");
                            info.add(in.nextLine());
                            System.out.print("Enter contact ten-digit phone number \"5555555555\"> ");
                            info.add(in.nextLine());
                            System.out.print("Enter contact email address> ");
                            info.add(in.nextLine());
                            System.out.print("Enter job title> ");
                            info.add(in.nextLine());
                            System.out.print("Enter organization name> ");
                            info.add(in.nextLine());
                            try
                            {
                                bContacts.add
                                (
                                    (
                                        new BusinessContact
                                        (
                                            info.get(0), info.get(1), info.get(2), info.get(3), info.get(4),
                                            info.get(5), info.get(6), info.get(7), info.get(8), info.get(9),
                                            info.get(10), info.get(11), info.get(12)
                                        )
                                    )
                                );
                            }
                            catch(IllegalArgumentException e)
                            {
                                System.out.println(e);
                            }
                            System.out.print("Create another business contact (Y or N): ");
                            String input = in.next();
                            if(input.toLowerCase().equals("n"))
                            {
                                complete = true;
                            }
                        }
                    }
                    else
                    {
                        System.out.println("How did you get here!?");
                    }
                }
                else
                {
                    System.out.printf("\n%s", err);
                    System.out.println();
                }
            }
            else
            {
                System.out.printf("\n%s", err);
                System.out.println();
                in.next(); // prevent infinite looping if non-int is given
            }
        }
    }
    /**
        Displays a short list of contacts using first and last name values.
        @param itr an iterator for a particular set of contacts.
    */
    public static void displayShortList(Iterator<Contact> itr)
    {
        int index = 1;
        while(itr.hasNext())
        {
            Contact temp = itr.next();
            System.out.printf("%s. %s %s\n", index, temp.getFname(), temp.getLname());
            index++;
        }
    }
	/**
		Iterates over a TreeSet of Contacts to printing
		their contents to the console.
		@param itr an iterator over a set of Contacts
	*/
    public static void displayContacts(Iterator<Contact> itr)
    {
        while(itr.hasNext())
        {
            ArrayList<String> temp = itr.next().getContact();
            for(int i = 0; i < temp.size(); i++)
            {
                String element = temp.get(i);
                if(element.equals(""))
                {
                    continue;
                }
                else
                {
                    System.out.println(element);
                }
            }
            System.out.println();
        }
    }
}
