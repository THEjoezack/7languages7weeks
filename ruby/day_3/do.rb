module ActsAsCsv
  def self.included(base)
    base.extend ClassMethods
  end

  module ClassMethods
    def acts_as_csv
      include InstanceMethods
    end
  end

  module InstanceMethods
    def read
      @csv_contents = []
      @csv_rows = []
      filename = self.class.to_s.downcase + '.txt'
      file = File.new(filename)
      @headers = file.gets.chomp.split(', ')

      file.each do |row|
        @csv_contents << row.chomp.split(', ')
        #Probably should have mapped this all at once
        @csv_rows << CsvRow.new(@headers, @csv_contents.last)
      end
    end

    def each &block
      @csv_rows.each &block
    end

    attr_accessor :headers, :csv_contents, :csv_rows
    def initialize
      read
    end
  end
end

class CsvRow
  attr_accessor :headers, :csv_contents

  def initialize headers, csv_contents
    @headers = headers
    @csv_contents = csv_contents
  end

  def method_missing name, *args
    index = @headers.index(name.to_s)
    puts @csv_contents[index]
  end  
end

class RubyCsv # no inheritance! You can mix it in
  include ActsAsCsv
  acts_as_csv
end

csv = RubyCsv.new
csv.each {|row| row.one}
