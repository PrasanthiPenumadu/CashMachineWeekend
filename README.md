# CashMachine
A simple cash account <-> bank system implementation (very loosely modelled!)

This project uses JavaFX for the user interface toolkit. JavaFX is a software platform for creating 
and delivering desktop applications, as well as rich Internet applications (RIAs) 
that can run across a wide variety of devices. It is part of the standard Java distribution.

Welcome to your first App. "ZipCloudBank", a local Wilmington fintech startup, has a minimally
viable product in CashMachine. The response from customers has been, shall we say, underwhelming.
The original coder has gone off to another startup, in Thailand, where they code on surfboards
all day long.

The Board of Directors of ZipCloudBank have empowered me to let you take a crack at "upping their game"
and improving their app over this weekend. Impressed that you are a ZipCode student, they are
expecting great things. Don't panic: we think you can do this.

The point of this lab is to read thru some existing code, in this case, a program/app that 
launches a window on your computer and lets you play with a couple of "banking accounts".
You should run the app, learn how to use it, so you understand what it does. It is not,
shall we say, user-friendly. So you may find what you have to do to make it work somewhat
awkward.

Then, you should read thru the code repository seeing how the code actually implements the 
things you see when you run the code. Find the "main" routine, which is where the app 
starts up. Trace from there to see what code gets called where and when. Trace how the 
operations of the code work.

Finally, you are to add some new functionality to the app, to make it "more useful, powerful &
more rewarding for the user".  
Your effort will be met with promotions, parades, and stock options. (*no, just kidding*).

## Lab Brief

Notice the structure of the current project before you start. Read thru the 
code up here.
##### Read thru the code. 
#### READ THRU THE CODE.
### READ THRU THE CODE.

Now:
- Find the 2 Account Classes: Basic, Premium
- Find the superclass of the 2 account classes.
- Find the other classes including Bank, Account, CashMachine. Read thru them all.

Read them all, trying to get an idea of how it all goes together.

Notice when reading the code...

- Each account has: id, name, email, balance; find that in a class.
- What does Premium account do that Basic does not?
- What are the two starter account already built into the project?
- Trace how the Cash machine sets the current account
-- enter account id and "login to account"
- Trace how a deposit happens
-- enter a number then click deposit
- Trace how a withdrawal happens
-- enter a number then click withdraw
- How does "exit" log out of the account?
- What might be a better name for "exit"?


### Things to Change for the Lab

FIRST, fork this repo into your own account.

After getting each of these items completed, be sure to do a 'git commit' to save you work. 
* Learn your git.
* Never Lose Code. 
* NEVER LOSE CODE. EVER. :-)

How about making a new git branch, named "dev" from the master branch for you to do all
your work in?? (what great idea!)

NOW, maybe you're ready to start making changes. 

* Add more accounts to the default constructor of the Bank class.
* When you overdraft an account, print an alert message to the areaInfo object on the window.

### Additional things to add

You need not do these things in this order. Do as many as you can. When you finish,
make **sure the project runs**. (Else the Board... well, disappointment lies that direction.)


* Make the account display more user friendly
  * add a Form layout that has separate TextFields for each piece of account info. 
  * You'll probably find a layout that lets you do this, a little like the FlowPane.
  * Make the login stuff more clear
  * Disable the three buttons that operate on an account until a login happens 
  * and then enable them. add another TextField for the amount entries. 
  * Make it different from the TextField where you set the account ID.

* Enable the amount entries to be floating point numbers instead of 
  just integers when doing deposits and withdrawals.

* Add a menu with a list of accounts in it and the menu action switched 
  to that account. You may find that JavaFX already has such a thing.

**** Add a New Account Window(!) that takes in the info required thru TextFields and creates the correct objects so that is can be changed like the pre-wired accounts.


NB: When googling for information on how to do all this, 
be sure you start every query with "javafx " and your other search terms. 
That will limit the results to things that probably are closer to what you need.