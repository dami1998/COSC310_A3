README FILE
This bot has three classes, findstate, response and main class.
MAIN CLASS
This class consist of code uses different states to determine what response are given to the user. We have 22 states. Our bot uses keywords to give a response based on the words the user uses. If a user gets to a point where the bot notices it can not go any further the bot reverts to the default state which asks the user whether they have other requests or questions. It also consists of the information like the list of products, shop locations and office hours. The bot can give a list product for the user to choose from to either get it shipped or reserved. The bot can give the list of shop locations and office hours. The bot can cancel and track orders using randomizer to determine whether a user has an order or not and whether a package is delivered, on its way or not yet shipped.
RESPONSE CLASS
This class is responsible for making the right response based on the current state of the conversation.
FINDSTATE CLASS
This class creates the flow of the conversation by determining the state of the current situation.
CHAT_SERVER CLASS
This is  where  the agent will be receiving the information/messages from the client
CHAT_CLIENT CLASS
This ios where the client will  be receiving the information/messages from the agent
CHATBOT CLASS
This  is where the GUI lies.
FEATURES OF THIS PROGRAM
1) track orders

2) Can give store  locations and time

3) Can  give the the user  the option to talk to an agent directly

4)Can reserve item

5) cancel an order

