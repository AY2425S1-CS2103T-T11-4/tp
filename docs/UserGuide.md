---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.

1. Download the latest `.jar` file from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `view` : Displays all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 t/High Risk m/Wheat d/25th July 1989` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.
   
   * `filter t/High Risk` : Displays all entries which are tagged High Risk.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `view`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS t/TAG m/ALLERGY …​`

**PHONE NUMBER**
- Must be exactly 8 digits long and start with 3, 6, 8 or 9.
- Only numeric characters are allowed

**EMAIL**
- Must follow a valid email format and include a domain like name@example.com
- Can contain alphanumeric characters like underscores, period and hyphens before the @ symbol

**ALLERGY** should only include alphanumeric characters, spaces, and commas.
**ALLERGY** must not be empty or contain special characters other than commas and spaces.
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A patient must have one tag (no more no less)
The tag must be one of the following
1. High Risk
2. Medium Risk
3. Low Risk
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 t/High Risk m/Insulin`
* `add n/Betsy-Crowe t/friend e/betsycrowe@example.com a/Newgate Estate #14-05 p/1234567 t/Low Risk m/None`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Locating persons by different parameters: `filter`

Finds persons whose information match any of the given parameters.

Format: `filter n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS t/TAG m/ALLERGY d/DATE [atleast one parameter]`

* Atleast one parameter is required
* Multiple parameters can be used to filter persons.
* Persons matching all parameters are returned (i.e. `AND` search).



### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Adding or updating an appointment date and time to a person : `date`

Adds or updates the next appointment date and time of the specified person in the address book.

Format: `date [n/NAME] [p/PHONE] [e/EMAIL] d/DATE`

* Adds or updates the next appointment date of person that uniquely matches at least one of the following three attributes `NAME`, `PHONE` and `EMAIL`
* `NAME` should only include alphanumeric characters, spaces, and hyphens
* `PHONE` must have 8 digits, starting with 3, 6, 8 or 9
* `EMAIL` must follow the format of [name]@[domain].[TLD]
* `DATE` must follow the format of dd/MM/YYYY HHmm
* If the attribute provided matches more than one person, two of the attributes need to be provided to uniquely match to a person
* To remove the date and time from a person, use `d/None`.

Examples:
* `date n/Jason Tan p/93823871 e/jasontan@gmail.com d/23/10/2024 1830`
* `date p/92938132 d/22/10/2024 1920`
* `date e/johndoe@gmail.com d/10/02/2023 1520`
* `date n/Alex Yeoh d/None`

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Locating persons by their features: `filter`

Filters the list to return persons who have the given features.

Format: `find PREFIX/FEATURE_NAME [PREFIX/FEATURE_NAME]`

* The search is case-sensitive.
* The order of the features does not matter. e.g. `t/ High Risk p/99999999` will match `p/99999999 t/ High Risk `
* In this version, you can only filter by tag and phone number
* Only full words will be matched e.g. `99999999` will not match `999`
* Persons matching all features listed will be returned (i.e. `AND` search).
* There can only be one of each feature as a maximum (ie cannot filter by two tags (eg. ‘filter t/ High Risk t/Low Risk’ is considered invalid format and not  accepted.
  *filter requires at least one feature to filter by (eg. ‘filter’ is an invalid format but ‘filter t/High Risk’ and ‘filter p/99999999’ are both accepted.
  e.g. `t/ High Risk p/99999999` will return all patients with tag High Risk and phone number 99999999


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS t/TAG m/ALLERGY d/DATE…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/High Risk t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Filter** | `filter n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS t/TAG m/ALLERGY d/DATE [atleast one parameter]`<br> e.g., `filter t/High Risk`
**View** | `view`
**Help** | `help`
**Appointment Date** | `date [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] d/DATE` 
**Schedule** | `schedule d/DATE` 