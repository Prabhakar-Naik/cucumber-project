Feature: Person testing feature

# first scenario  to initializing the list of persons
Scenario Outline: Initializing a given list of persons
    Given I have static methods which initializing a list of persons
    When I Connect I can see the size of the initialized list of person
    Then The list size equal to 3

# create a new person with random entries
Scenario Outline: Creating a new person
    Given The list of persons contain 3 persons already stored
    When I create a new person with random entrie
    Then I get the ID of the new person and the list contain more than 3 persons

# Person Bulk creation
Scenario Outline: Bulk creation
    Given The list of persons contain 3 persons already stored
    When I created new person with <FirstName> and <LastName> and <Age>
    Then I get the ID of the new person and the list contains more than 3 persons

    # Examples:
    #     | FirstName | LastName   | Age |
    #     | prabhakar | kimavath   | 25  |
    #     | sudhakar  | naik       | 29  |
    #     | veerababu | panasa     | 27  |
    #     | suresh    | vajjapally | 27  |

# updating on existed person
Scenario Outline: Update a person by ID
    Given The list of persons contain 3 persons already stored
    When I update a person data with <ID> and <FirstName> and <LastName> and <Age>
    Then I get the person Updated

    Examples:
        |ID|| FirstName  | LastName    | Age |
        |1 || prabhakar1 | kimavath1   | 26  |
        |2 || sudhakar2  | naik1       | 27  |
        |4 || veerababu3 | panasa1     | 29  |
        |5 || suresh4    | vajjapally1 | 29  |

# Delete an existing person
Scenario Outline: Delete a given person
    Given The list of persons contain 3 persons already stored
    When I delete a person with ID "1"
    Then The given person is deleted and the list size is equal to "2"