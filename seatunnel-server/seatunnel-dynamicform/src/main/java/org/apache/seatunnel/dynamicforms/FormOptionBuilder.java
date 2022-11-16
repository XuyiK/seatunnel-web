package org.apache.seatunnel.dynamicforms;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class FormOptionBuilder {

    private String label;

    private String field;

    public static FormOptionBuilder builder() {
        return new FormOptionBuilder();
    }

    public FormOptionBuilder withLabel(@NonNull String label) {
        this.label = label;
        return this;
    }

    public FormOptionBuilder withI18nLabel(@NonNull String label) {
        this.label = Locale.I18N_PREFIX + label;
        return this;
    }

    public FormOptionBuilder withField(@NonNull String field) {
        this.field = field;
        return this;
    }

    public InputOptionBuilder inputOptionBuilder() {
        return new InputOptionBuilder(label, field);
    }

    public DynamicSelectOptionBuilder dynamicSelectOptionBuilder() {
        return new DynamicSelectOptionBuilder(label, field);
    }

    public StaticSelectOptionBuilder staticSelectOptionBuilder() {
        return new StaticSelectOptionBuilder(label, field);
    }

    public static class InputOptionBuilder {
        private String label;

        private String field;

        public InputOptionBuilder(@NonNull String label, @NonNull String field) {
            this.label = label;
            this.field = field;
        }

        public FormInputOption formTextInputOption() {
            return new FormInputOption(FormInputOption.InputType.TEXT, label, field);
        }

        public FormInputOption formPasswordInputOption() {
            return new FormInputOption(FormInputOption.InputType.PASSWORD, label, field);
        }

        public FormInputOption formTextareaInputOption() {
            return new FormInputOption(FormInputOption.InputType.TEXTAREA, label, field);
        }
    }

    public static class DynamicSelectOptionBuilder {
        private String label;

        private String field;

        private String selectApi;

        public DynamicSelectOptionBuilder(@NonNull String label, @NonNull String field) {
            this.label = label;
            this.field = field;
        }

        public DynamicSelectOptionBuilder withSelectApi(@NonNull String selectApi) {
            this.selectApi = selectApi;
            return this;
        }

        public DynamicSelectOption formDynamicSelectOption() {
            return new DynamicSelectOption(selectApi, label, field);
        }
    }

    public static class StaticSelectOptionBuilder {
        private String label;

        private String field;

        private List<AbstractFormSelectOption.SelectOption> options = new ArrayList<>();

        public StaticSelectOptionBuilder(@NonNull String label, @NonNull String field) {
            this.label = label;
            this.field = field;
        }

        public StaticSelectOptionBuilder addSelectOptions(
            @NonNull AbstractFormSelectOption.SelectOption... selectOptions) {
            for (AbstractFormSelectOption.SelectOption option : selectOptions) {
                options.add(option);
            }
            return this;
        }

        public StaticSelectOption formStaticSelectOption() {
            return new StaticSelectOption(options, label, field);
        }
    }
}