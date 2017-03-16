# VOXSPELL
INTRODUCTION
------------
Welcome to VOXSPELL spelling aid: An interactive graphical user interface which allows the user to test out and improve
their spelling skills.

HOW TO USE THE GRAPHICAL USER INTERFACE
---------------------------------------
Unzip file stim599_VOXSPELL.zip.
Run the script file through command : ./run.sh
If in case, that gives an error, try first changing the permissions of the file through command : chmod 777 and then run the same command as above whilst being in the directory of stim599_VOXSPELL.

When you run the script file, you will be asked to pick a character whose purpose will be to guide you through the application
as well some other surprises (check below for more) ! After which, you will be lead to a main menu screen.

Simply press on the buttons to be taken to the desired mode, at this stage. Remember to select the level from the drop
down menu to do a new quiz for that particular level. Levels only apply when you want to start a new quiz. 

NOTE
----
Be sure to click everything only ONCE!

ASPECTS OF THE MAIN MENU
------------------------

SETTINGS
--------
Pressing clear will clear out all the files which means starting the prototype with a clean slate. So any
statistics that existed for each of the level and any prior settings such as voice choice will be deleted
and be set as default.

You can choose between two voices (festival diphones) to use the prototype with; this can be done using the drop down
menu and then pressing apply changes. You can even hear a preview of the selected voice when apply changes is
pressed.

There is also an option to pick your own spelling list. However, your spelling list must match the format given below
which states that the sublists have to be divided into levels (maximum of 11) and at every start of a level the text
file should read “%Level [number]” for example,

%Level 1
[word]
[word]
[word]
%Level 2
[word]
[word]
[word]
[word]
[word]

the above list has 2 levels

Press the back button to go back to the main menu.

NEW SPELLING
------------
Before selecting this option make sure you have chosen your desired level from the drop down menu present in the
main menu. Once you have done this and pressed the new spelling button you will then be taken to the game play.
Here you can find a back button to go back to the main menu anytime and next to the a text box where you enter your
spelling for it to be checked. On top of this you see the current level number is shown. Next to this the score for
the current new spelling play through is shown and this number indicates the number of mastered words so far out of 10
in this current play through of new spelling. It also shows how many words are left for the user to spell Unless they 
clear these stats through settings these accuracy rates for the different levels are not changed. Under this is a 
re-listen/listen again button which the user can use at anytime to listen to the word again.

The actual checking of the spelling occurs when the user presses the submit button after typing their attempt of the word
into the text box. This answer can be correct in which case the user presses the next work button. If it is incorrect,
the user gets a second chance to spell the word and the word is now said twice. To check this spelling, the user then presses
the try again button. This can either be correct or incorrect and this is said and displayed on the screen but no matter the
outcome the user then has the chance to go to the next word by pressing the next word button. If the score for the level is 9
or greater the user then has reached the end of the level meaning they are able to either practice the level again or they can
go to the next level or they can watch a video reward. Once watching the video reward they are returned to this so they can still
select practice or go to next level. In the play video window the video is played and the user can pause and play the video and
mute and well as watch an inverted version of the video by pressing the watch additional video reward. Once they are done they close
the window and either go back to main or practice or go to next level. If the user however hasn’t gotten 9 out of 10 or more correct,
they are by default sent back to the main menu.

REVIEW
------
This is similar to new spelling, however the user simply gets to spell all the words that they had failed in previous new spelling
quizzes. So any words that they had gotten wrong (incorrect twice) from any of the levels are all asked again one by one in this
section. This is done through the same procedure as new quiz by typing in their attempt in the text box and pressing the submit and try
again buttons and pressing next word button for the next word. Just as in new spelling the user can re-listen/listen to the word using the
re-listen/listen again button and go back to main menu at any time using the back button.
At the end of this session ( or mastering all the failed words ), there is a reward awaiting for the user which lets the user play a game. When the user clicks on the “Play a Game” button, a small window will appear that lets the user choose the difficulty at which they want to play the game. After which the user can play the game as their chosen character. Losing this game, would lead to the main menu. 

STATISTICS/(to)Look how well you are doing!
--------------------------------------------
This mode will lead you to a screen that displays how well the user is doing from best to worst performance in levels. This has been done in a simple ___ out of total format for easier interpretation. 


REWARDS
------
Be sure to not miss out on any rewards whilst playing the game! At the completion of New Spelling and Review, there are special rewards for the user.
