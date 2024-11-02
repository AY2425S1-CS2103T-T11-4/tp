package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_EMAIL_DETAILS;
import static seedu.address.logic.Messages.MESSSAGE_INVALID_PHONE_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Date;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;


/**
 * Parses input arguments and creates a new {@code DateCommand} object
 */
public class DateCommandParser implements Parser<DateCommand> {
    private static final String DATE_PATTERN =
            "^([1-9]|[12][0-9]|3[01])/([1-9]|1[0-2])/\\d{4} ([01][0-9]|2[0-3])[0-5][0-9]$";
    private static final String FORMAT_PATTERN = "^\\d{1,2}/\\d{1,2}/\\d{4} \\d{4}$";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    /**
     * Parses the given {@code String} of arguments in the context of the {@code DateCommand}
     * and returns a {@code DateCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DateCommand parse(String args) throws ParseException {
        try {
            requireNonNull(args);
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                    PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DATE);
            if (!areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL)
                    && !areAnyPrefixesPresent(argMultimap, PREFIX_DATE)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DateCommand.MESSAGE_USAGE));
            }

            Optional<String> name = argMultimap.getValue(PREFIX_NAME);
            Optional<String> phone = argMultimap.getValue(PREFIX_PHONE);
            Optional<String> email = argMultimap.getValue(PREFIX_EMAIL);
            String dateString = argMultimap.getValue(PREFIX_DATE).orElse("");
            LocalDateTime date = ParserUtil.parseDateString(dateString);

            // Validate the phone number format if present
            if (phone.isPresent() && !Phone.isValidPhone(phone.get())) {
                throw new ParseException(MESSSAGE_INVALID_PHONE_DETAILS);
            }

            // Validate the email format if present
            if (email.isPresent() && !Email.isValidEmail(email.get())) {
                throw new ParseException(MESSAGE_INVALID_EMAIL_DETAILS);
            }

            return new DateCommand(name, phone, email, new Date(date));
        } catch (IllegalValueException ive) {
            throw new ParseException(ive.getMessage(), ive);
        }
    }

    /**
     * Returns true if at least one of the specified prefixes is present in the
     * given ArgumentMultimap.
     */
    private boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        for (Prefix prefix : prefixes) {
            if (argumentMultimap.getValue(prefix).isPresent()) {
                return true;
            }
        }
        return false;
    }

}
